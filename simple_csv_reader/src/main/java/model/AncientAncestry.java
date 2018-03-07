package main.java.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * created by Smita Hasole on 02-03-2018
 */

public class AncientAncestry {

    private String sampleID;

    private String SuperPop_CODE;

    private Double neanderthal_percent;

    private Double Denisovan_percent;

   public AncientAncestry( String sampleID, String sup, Double nen, Double Den){
        this.sampleID = sampleID;
        this.SuperPop_CODE = sup;
        this.neanderthal_percent = nen;
        this.Denisovan_percent = Den;
    }
   public AncientAncestry(){}

    @Override
    public String toString() {
        return "AncientAncestry{" +
                "sampleID='" + sampleID + '\'' +
                ", SuperPop_CODE='" + SuperPop_CODE + '\'' +
                ", neanderthal_percent='" + neanderthal_percent + '\'' +
                ", Denisovan_percent='" + Denisovan_percent + '\'' +
                '}';
    }

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getSuperPop_CODE() {
        return SuperPop_CODE;
    }

    public void setSuperPop_CODE(String superPop_CODE) {
        SuperPop_CODE = superPop_CODE;
    }

    public Double getNeanderthal_percent() {
        return neanderthal_percent;
    }

    public void setNeanderthal_percent(Double neanderthal_percent) {
        this.neanderthal_percent = neanderthal_percent;
    }

    public Double getDenisovan_percent() {
        return Denisovan_percent;
    }

    public void setDenisovan_percent(Double denisovan_percent) {
        Denisovan_percent = denisovan_percent;
    }
}
