package com.my.dental.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.my.dental.core.dto.MensajeDTO;
import com.my.dental.core.dto.ParametroDTO;
import com.my.dental.core.entity.CitaEntity;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.entity.MedicoEntity;
import com.my.dental.core.entity.ParametroEntity;
import com.my.dental.repository.CitaRepository;
import com.my.dental.repository.ClienteRepository;
import com.my.dental.repository.MedicoRepository;
import com.my.dental.repository.ParametroRepository;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MessageServiceImpl implements MessageService {

	@Value("${correo.email}")
	private String correoEmail;

	@Value("${correo.password}")
	private String correoPassword;

    private final CitaRepository citaRepository;
    private final ParametroService parametroService;
    private final ClienteRepository clienteRepository;
    private final MedicoRepository medicoRepository;
    private final ParametroRepository parametroRepository;

    public MessageServiceImpl(CitaRepository citaRepository, ParametroService parametroService, ClienteRepository clienteRepository, MedicoRepository medicoRepository, ParametroRepository parametroRepository) {
        this.citaRepository = citaRepository;
        this.parametroService = parametroService;
        this.clienteRepository = clienteRepository;
        this.medicoRepository = medicoRepository;
        this.parametroRepository = parametroRepository;
    }

    @Override
    public void envioCorreo(List<MensajeDTO> listCitas)  {
        listCitas.forEach(msj -> MessageServiceImpl.envioCorreoProcess(msj, this.citaRepository, this.parametroService,
                this.clienteRepository, this.medicoRepository, this.parametroRepository, correoEmail, correoPassword));
    }


    public static void envioCorreoProcess(MensajeDTO mensajePrm, CitaRepository citaRepository, ParametroService parametroService,
                                   ClienteRepository clienteRepository, MedicoRepository medicoRepository,
                                   ParametroRepository parametroRepository, String correoEmailPrm, String correoPasswordPrm)  {
        try {
            Optional<CitaEntity> citaEntity = citaRepository.findById(mensajePrm.getIdCita());
            CitaEntity cita = citaEntity.get();

            Optional<ClienteEntity> clienteEntity = clienteRepository.findById(cita.getCliente().getId());
            Optional<MedicoEntity> medicoEntity = medicoRepository.findById(cita.getMedico().getId());
            Optional<ParametroEntity> parametroSedeEntity = parametroRepository.findById(cita.getIdParametroSede());


            ClienteEntity cliente = clienteEntity.get();
            MedicoEntity medico = medicoEntity.get();
            ParametroEntity sede = parametroSedeEntity.get();

            String[] resultado = obtenerFechaYHora(cita.getFechaCita());
            String fecha = resultado[0];
            String hora = resultado[1];

            MensajeDTO mensajeDTO = MensajeDTO.builder()
                    .paciente(cliente.getNombres().concat(" ").concat(cliente.getApellidos()))
                    .correo(cliente.getCorreo())
                    .lugar(sede.getDescripcion())
                    .fecha(fecha)
                    .hora(hora)
                    .medico(medico.getNombres().concat(" ").concat(medico.getApellidos()))
                    .tratamiento(cita.getMotivo())
                    .build();

            List<String> lstTipos = new ArrayList<>();
            lstTipos.add("TEMPLATE EMAIL");
            lstTipos.add("LOGO");
            List<ParametroDTO> lstParametro = parametroService.findByTipoIn(lstTipos);




            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correoEmailPrm, correoPasswordPrm);
                        }
                    });

            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEmailPrm));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clienteEntity.get().getCorreo()));
            message.setSubject("Cita Dental Programada " + mensajeDTO.getFecha());


//            String imagePath = "images/dentalMail.jpg"; // Cambia esto a la ruta de tu imagen
//            String base64Image = convertImageToBase64(imagePath);

//            String base64Image = loadImageFromResources("images/dentalMail.jpg");


            Optional<ParametroDTO> paramTemplate = lstParametro.stream()
                                                            .filter(p -> p.getTipo().equals("TEMPLATE EMAIL"))
                                                            .findFirst();
            String htmlTemplate = paramTemplate.get().getFormato();

            Optional<ParametroDTO> paramLogo = lstParametro.stream()
                                                            .filter(p -> p.getTipo().equals("LOGO"))
                                                            .findFirst();
            String base64Image = paramLogo.get().getFormato();


            htmlTemplate = htmlTemplate.replace("${paciente}", mensajeDTO.getPaciente())
                    .replace("${lugar}", mensajeDTO.getLugar())
                    .replace("${fecha}", mensajeDTO.getFecha())
                    .replace("${hora}", mensajeDTO.getHora())
                    .replace("${medico}", mensajeDTO.getMedico())
                    .replace("${tratamiento}", mensajeDTO.getTratamiento());

            // Crear el cuerpo del mensaje en formato HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlTemplate, "text/html; charset=UTF-8");

            // Crear el archivo adjunto (imagen)
            MimeBodyPart imagePart = new MimeBodyPart();
//            DataSource fds = getImageDataSource("images/dentalMail.jpg");  // Reemplaza con la ruta de tu imagen
            DataSource fds = new ByteArrayDataSource(Base64.getDecoder().decode(base64Image), "image/jpg");
            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.setHeader("Content-ID", "<image1>");
            imagePart.setFileName("imagen.jpg");

            // Crear el multipart para el mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(imagePart);

            // Establecer el contenido del mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado:");
            e.printStackTrace();
        }

    }

    // Método para cargar la imagen desde el archivo resources y convertirla a Base64
    public static String loadImageFromResources(String imagePath) throws IOException {
        ClassPathResource imgFile = new ClassPathResource(imagePath);
        InputStream inputStream = imgFile.getInputStream();
        byte[] fileContent = inputStream.readAllBytes();
        inputStream.close();
        return Base64.getEncoder().encodeToString(fileContent);
    }


    public static String convertImageToBase64(String imagePath) throws IOException {
        // Acceder al archivo desde la carpeta resources
        ClassPathResource imgFile = new ClassPathResource(imagePath);
        InputStream inputStream = imgFile.getInputStream();
        if (inputStream == null) {
            throw new IOException("El archivo no se encuentra en el path especificado.");
        }

        // Leer el contenido del InputStream
        byte[] fileContent = new byte[inputStream.available()];
        inputStream.read(fileContent);
        inputStream.close();

        return Base64.getEncoder().encodeToString(fileContent);
    }

    private static DataSource getImageDataSource(String imagePath) {
        try {
            ClassPathResource imgFile = new ClassPathResource(imagePath);
            InputStream is = imgFile.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            baos.flush();
            return new ByteArrayDataSource(baos.toByteArray(), "image/jpeg");  // Ajusta el tipo de imagen según el archivo
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] obtenerFechaYHora(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String fecha = dateTime.format(dateFormatter);
        String hora = dateTime.format(timeFormatter);
        return new String[]{fecha, hora};
    }


}
