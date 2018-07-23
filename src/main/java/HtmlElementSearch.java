import analyzer.ChangeAnalyzer;
import analyzer.OriginDocumentAnalyzer;
import domain.ElementInfo;

import java.util.Optional;

public class HtmlElementSearch {

    public static void main(String[] args) {
        Optional<ElementInfo> result = OriginDocumentAnalyzer.fiendInfoAboutOriginElement(args[0], args[1]);
        System.out.println(ChangeAnalyzer.analyze(args[2], result.get()));
    }
}
