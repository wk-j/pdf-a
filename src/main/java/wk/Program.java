package wk;

import java.io.FileInputStream;
import org.verapdf.pdfa.Foundries;
import org.verapdf.pdfa.PDFAParser;
import org.verapdf.pdfa.PDFAValidator;
import org.verapdf.pdfa.VeraGreenfieldFoundryProvider;
import org.verapdf.pdfa.results.ValidationResult;
import org.verapdf.pdfa.validation.profiles.ProfileDetails;
import static java.lang.System.out;

public class Program {
    public static void main(String[] args) throws Exception {
        VeraGreenfieldFoundryProvider.initialise();
        try (PDFAParser parser = Foundries.defaultInstance().createParser(new FileInputStream("pdf/a.pdf"))) {
            PDFAValidator validator = Foundries.defaultInstance().createValidator(parser.getFlavour(), false);
            ValidationResult result = validator.validate(parser);
            if (result.isCompliant()) {
                ProfileDetails detail = result.getProfileDetails();
                out.println("Name: " + detail.getName());
                out.println("Creator: " + detail.getCreator());
                out.println("Description: " + detail.getDescription());
            } else {

            }
        }
    }
}