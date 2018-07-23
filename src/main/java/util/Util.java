package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Util {
    public static String CHARSET_NAME = "utf8";
    public static String SPLIT_REGEX = ", ";

    public static Optional<String> attributeToStringOpt(Optional<Element> elementOpt) {
       return elementOpt.map(button ->
                button.attributes().asList().stream()
                        .map(attr -> attr.getKey() + " = " + attr.getValue())
                        .collect(Collectors.joining(", "))
        );
    }

    public static Optional<Element> findElementById(File htmlFile, String targetElementId) throws IOException {
            Document doc = Jsoup.parse(
                    htmlFile,
                    Util.CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.of(doc.getElementById(targetElementId));
    }
}
