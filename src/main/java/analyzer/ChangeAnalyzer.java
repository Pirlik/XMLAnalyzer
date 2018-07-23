package analyzer;

import domain.ElementInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.Util.SPLIT_REGEX;

public class ChangeAnalyzer {
    private static Logger LOGGER = LoggerFactory.getLogger(ChangeAnalyzer.class);


    public static Optional<String> analyze(String filePath, ElementInfo elementInfo) {
        File htmlFile = new File(filePath);
        int numberOfEqAttr = 0;
        Optional<String> xPath = Optional.empty();

        try {
            Document targetDoc = Jsoup.parse(
                    htmlFile,
                    Util.CHARSET_NAME,
                    htmlFile.getAbsolutePath());
            for (Element e : targetDoc.body().getElementsByTag(elementInfo.getTargetTag())) {
                int counter = approximatelyCompareValues(elementInfo.getTargetElementAttrOpt(), Util.attributeToStringOpt(Optional.of(e)));
                if(numberOfEqAttr < counter){
                    numberOfEqAttr = counter;
                    xPath = Optional.of(e.parents().stream().map(el -> el.tagName() + " class[ " + el.className() + " ]").collect(Collectors.joining(" < ")));
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
        }

        if(xPath.isPresent()) {
            LOGGER.info("XML path to the element within the diff-case HTML file - " + xPath);
        } else {
            LOGGER.warn("XML path wasn't found");
        }
        return xPath;
    }

    private static int approximatelyCompareValues(Optional<String> origin, Optional<String> target) {
        if (!origin.isPresent() || !target.isPresent()) {
            return 0;
        }
        String[] originArr = origin.get().toLowerCase().split(SPLIT_REGEX);
        String[] targetArr = target.get().toLowerCase().split(SPLIT_REGEX);
        int counter = 0;

        for (String originEl : originArr) {
            for (String targetEl : targetArr) {
                if (originEl.equals(targetEl)) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }
}
