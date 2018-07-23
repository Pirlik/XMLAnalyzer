package analyzer;

import domain.ElementInfo;
import org.jsoup.nodes.Element;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OriginDocumentAnalyzer {
    private static Logger LOGGER = Logger.getLogger(OriginDocumentAnalyzer.class.getName());

    public static Optional<ElementInfo> fiendInfoAboutOriginElement(String resourcePath, String targetElementId) {
        ElementInfo targetElementInfo = new ElementInfo();
        Optional<Element> targetElementOpt;
        try {
            targetElementOpt = Util.findElementById(new File(resourcePath), targetElementId);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,"Error reading [{ " + resourcePath + " }] file", e);
            return Optional.empty();
        }
        if (targetElementOpt.isPresent()) {
            targetElementInfo.setTargetTag(targetElementOpt.get().tagName());
            targetElementInfo.setTargetElementAttrOpt(Util.attributeToStringOpt(targetElementOpt));
            return Optional.of(targetElementInfo);
        }

        return Optional.empty();
    }
}
