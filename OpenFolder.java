
import java.nio.file.*;
import java.io.File;
import java.io.IOException;

public class OpenFolder{
    private static String path;
    public static void main(String[] args){
        path = Paths.get(".").toAbsolutePath().normalize().toString();
System.out.println(path);
        openFolder(path);
    }
    private static void openFolder(String argPath){
        File dirToOpen = null;
        try{
        dirToOpen = new File(argPath);		
        File[] fileList = dirToOpen.listFiles(); 
		System.out.println(fileList.length);
        for(File file: fileList){
            if(!file.isFile()){
                moveFiles(file);
            }
        }
        }catch (IllegalArgumentException iae) {
            System.out.println("File Not Found");
        }
    }
    private static void moveFiles(File argFile){
        for(File file: argFile.listFiles()){
            if(file.isFile()){
                try {
                  Files.move(Paths.get(file.getAbsolutePath()), Paths.get(path+"\\"+file.getName()), StandardCopyOption.ATOMIC_MOVE);
                }
                catch (IOException ex) {
                  ex.printStackTrace();
                }
                }else{
                    openFolder(file.getPath());
                }
            }
			file.delete();
        }
    }