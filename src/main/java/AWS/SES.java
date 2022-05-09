package AWS;

import java.io.IOException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.record.Record;

public class SES {


    static final String FROM = "email.SES@AWS.com";


    static final String TO = "email.destino@exemplo.com";


    static final String SUBJECT = "Teste";


    static final String TEXTBODY = "Teste email com kafka.";

    public static void sendEmail(ConsumerRecord<String, String> record) throws IOException {

        final String HTMLBODY = "<h1>Teste de Email com Kafka</h1>"
                + "<p> value = " + record.value() + "<br>"
                + "key = " + record.key()  + "<br>"
                + "offset = " + record.offset() + "</p>";


        // não esqueça de estar com o "standard client" configurado na máquina e de inserir a região do email SES que vai usar.
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()

                            .withRegion(Regions.US_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);

            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}