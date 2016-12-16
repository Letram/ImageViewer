package imageviewer.persistence.files;

import imageviewer.model.Image;
import imageviewer.persistence.ImageLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileImageLoader implements ImageLoader{

    private final File[] files;

    public FileImageLoader(String folder) {
        this.files = new File(folder).listFiles(withImageExtension());
    }
    
    @Override
    public Image load() {
        return imageAt(0);
    }
    private Image imageAt(final int index){
        return new Image(){
            @Override
            public byte[] bitmap() {
                try {
                    FileInputStream is = new FileInputStream(files[index]);
                    return read(is);
                } catch (IOException ex) {
                    return new byte[0];
                }
            }

            private byte[] read(FileInputStream is) throws IOException {
                byte[] buffer = new byte[4096];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while(true){
                    int length = is.read(buffer);
                    if(length < 0)break;
                    os.write(buffer, 0, length);
                }
                return os.toByteArray();
            }

            @Override
            public Image next() {
                return imageAt(index+1);
            }

            @Override
            public Image prev() {
                return imageAt(index-1);
            }
            
        };
    }

    private FilenameFilter withImageExtension() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
