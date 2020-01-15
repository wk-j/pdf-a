package wk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.verapdf.core.EncryptedPdfException;
import org.verapdf.core.ModelParsingException;
import org.verapdf.core.ValidationException;
import org.verapdf.pdfa.Foundries;
import org.verapdf.pdfa.PDFAParser;
import org.verapdf.pdfa.PDFAValidator;
import org.verapdf.pdfa.VeraGreenfieldFoundryProvider;
import org.verapdf.pdfa.flavours.PDFAFlavour;
import org.verapdf.pdfa.results.TestAssertion;
import org.verapdf.pdfa.results.ValidationResult;
import org.verapdf.pdfa.validation.profiles.ProfileDetails;

public class Program {
    public static void main(String[] args) throws ModelParsingException, EncryptedPdfException, FileNotFoundException,
            ValidationException, IOException {
        VeraGreenfieldFoundryProvider.initialise();
        // try (PDFAParser parser = Foundries.defaultInstance().createParser(new
        // FileInputStream("pdf/sample.pdf"))) {
        try (PDFAParser parser = Foundries.defaultInstance().createParser(new FileInputStream("pdf/2.pdf"))) {
            PDFAValidator validator = Foundries.defaultInstance().createValidator(parser.getFlavour(), false);
            ValidationResult result = validator.validate(parser);
            if (result.isCompliant()) {
                // File is a valid PDF/A 1b
                System.out.println(("Yes"));
                ProfileDetails detail = result.getProfileDetails();

                System.out.println("Name: " + detail.getName());
                System.out.println("Description: " + detail.getDescription());
                System.out.println("Creator: " + detail.getCreator());

                Set<TestAssertion> tests = result.getTestAssertions();
                for (TestAssertion t : tests) {
                    System.out.print(t.getStatus());
                    System.out.print(t.getRuleId());
                    System.out.println(t.getMessage());
                }
            } else {
                // it isn't
                System.out.println("No");
            }
        }
    }
}