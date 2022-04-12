package cosc202.andie.unitTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import cosc202.andie.ImagePanel;

public class ImagePanelTest {
    @Test
    public void initialDummyTest(){

    }
    @Test
    public void getZoomInitialValue(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }
    @Test
    public void getZoomAfterSetZoom0(){
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }
}
