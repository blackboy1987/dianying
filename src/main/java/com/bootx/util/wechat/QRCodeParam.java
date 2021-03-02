package com.bootx.util.wechat;

public class QRCodeParam {

    private String scene;
    private String page;
    private Integer width=430;
    private Boolean autoColor=false;
    private LineColor lineColor=null;

    private Boolean isHyaline;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getAutoColor() {
        return autoColor;
    }

    public void setAutoColor(Boolean autoColor) {
        this.autoColor = autoColor;
    }

    public LineColor getLineColor() {
        return lineColor;
    }

    public void setLineColor(LineColor lineColor) {
        this.lineColor = lineColor;
    }

    public Boolean getIsHyaline() {
        return isHyaline;
    }

    public void setIsHyaline(Boolean isHyaline) {
        this.isHyaline = isHyaline;
    }


    public static class LineColor {
        private Integer r=0;

        private Integer g=0;

        private Integer b=0;

        public Integer getR() {
            return r;
        }

        public void setR(Integer r) {
            this.r = r;
        }

        public Integer getG() {
            return g;
        }

        public void setG(Integer g) {
            this.g = g;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }
    }



    public static QRCodeParam create(String scene){
        QRCodeParam qrCodeParam = new QRCodeParam();
        qrCodeParam.setScene(scene);
        return qrCodeParam;
    }
}

