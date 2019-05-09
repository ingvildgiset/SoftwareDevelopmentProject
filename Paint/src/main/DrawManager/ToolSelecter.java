package DrawManager;

import java.awt.*;

public class ToolSelecter {
    private ShapeTool shapeTool;
    private Color penColor;
    private boolean fill;
    private Color fillColor;

    ToolSelecter(){
        //setting default tool values
        shapeTool = ShapeTool.LINE;
        penColor = Color.black;
        fill = false;
        fillColor  = Color.white;
    }


    //tror at hvis vi lager disse funksjonene static så kan de kalles fra ToolBar og ColorBar uten å ha et objekt.

    static public void setShape(ShapeTool shape) {
    }

    static public void setPenColor(Color penColor) {

    }

    static public void setFill(boolean fill) {

    }

    static public void setFillCOlor(Color fillColor) {

    }
}
