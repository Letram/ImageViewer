package imageviewer;

import control.NextImageCommand;
import control.PrevImageCommand;
import imageviewer.persistence.ImageLoader;
import imageviewer.persistence.files.FileImageLoader;

public class Main {

    public static void main(String[] args) {
        ImageLoader imageLoader = new FileImageLoader("");
        MainFrame mainFrame = new MainFrame();
        mainFrame.add(new NextImageCommand(mainFrame.getImageDisplay()));
        mainFrame.add(new PrevImageCommand(mainFrame.getImageDisplay()));
        mainFrame.getImageDisplay().display(imageLoader.load());
    }
}
