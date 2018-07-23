package domain;

import java.util.Optional;

public class ElementInfo {
    private String targetTag;
    private Optional<String> targetElementAttrOpt;

    public String getTargetTag() {
        return targetTag;
    }

    public void setTargetTag(String targetTag) {
        this.targetTag = targetTag;
    }

    public Optional<String> getTargetElementAttrOpt() {
        return targetElementAttrOpt;
    }

    public void setTargetElementAttrOpt(Optional<String> targetElementAttrOpt) {
        this.targetElementAttrOpt = targetElementAttrOpt;
    }
}
