package banex;

import java.net.*;
import java.io.*;
import java.util.Random;

public class AnekGetter{
    private static final String URLtemplate = "https://baneks.ru/";

    private Integer anek_num;

    private Random randgen;
    
    public void getAnekNum(){
        anek_num = randgen.nextInt(1141) + 1;
    };

    public void nextAnek(){
        this.getAnekNum();
        String surl = URLtemplate + anek_num.toString();
        try{

            URL url = new URL(surl);
            
            InputStream is = (InputStream) url.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();

            
            while((line = br.readLine()) != null){
                if (line.contains("<article>")) {
                    line = br.readLine();
                    while(line.contains("</article>") == false){
                        line = line.replace("<h2>", "").replace("</h2>", "").replace("<br />", "").replace("<p>", "").replace("</p>", "");
                        //System.out.println(line);
                        sb.append(line).append("\n");
                        line = br.readLine();
                    }  
                }
            }
            String htmlContent = sb.toString();
            System.out.println(htmlContent);
                 
        }
        catch(SocketTimeoutException e) {
				System.out.println(e.toString());
			}
            catch(IOException e){
                System.out.println(e.toString());
            }
    }
    
    AnekGetter(){
        randgen = new Random();
    }
}
