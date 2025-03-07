import java.util.*;
import java.io.*;
import java.net.*;

public class LeituraHtml {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Nome_Site = sc.nextLine();
        String Site = sc.nextLine();
        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;
        int a2 = 0; // 'á'
        int e2 = 0; // 'é'
        int i2 = 0; // 'í'
        int o2 = 0; // 'ó'
        int u2 = 0; // 'ú'
        int a3 = 0; // 'ã'
        int e3 = 0; // 'è'
        int i3 = 0; // 'ì'
        int o3 = 0; // 'ò'
        int u3 = 0; // 'ù'
        int a4 = 0; // 'â'
        int o4 = 0; // 'ô'
        int a5 = 0; // 'ê'
        int e5 = 0; // 'ê'
        int i5 = 0; // 'î'
        int o5 = 0; // 'ô'
        int u5 = 0; // 'û'

        int consoantes = 0;
        int br1 = 0;
        int table = 0;
        while (!Nome_Site.equals("FIM")) 
        {

            try {
                URL urlsite = new URL(Site);
                HttpURLConnection conn = (HttpURLConnection) urlsite.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    for (int n = 0; n < line.length(); n++) {
                        if (line.charAt(n) == 'a') {
                            a++;
                        } else if (line.charAt(n) == 'e') {
                            e++;
                        } else if (line.charAt(n) == 'i') {
                            i++;
                        } else if (line.charAt(n) == 'o') {
                            o++;
                        } else if (line.charAt(n) == 'u') {
                            u++;
                        } else if (line.charAt(n) == '\u00e1') {
                            a2++;
                        } else if (line.charAt(n) == '\u00e9') {
                            e2++;
                        } else if (line.charAt(n) == '\u00ed') {
                            i2++;
                        } else if (line.charAt(n) == '\u00f3') {
                            o2++;
                        } else if (line.charAt(n) == '\u00fa') {
                            u2++;
                        } else if (line.charAt(n) == '\u00e0') {
                            a3++;
                        } else if (line.charAt(n) == '\u00e8') {
                            e3++;
                        } else if (line.charAt(n) == '\u00ec') {
                            i3++;
                        } else if (line.charAt(n) == '\u00f2') {
                            o3++;
                        } else if (line.charAt(n) == '\u00f9') {
                            u3++;
                        } else if (line.charAt(n) == '\u00e3') {
                            a4++;
                        } else if (line.charAt(n) == '\u00f5') {
                            o4++;
                        } else if (line.charAt(n) == '\u00e2') {
                            a5++;
                        } else if (line.charAt(n) == '\u00ea') {
                            e5++;
                        } else if (line.charAt(n) == '\u00ee') {
                            i5++;
                        } else if (line.charAt(n) == '\u00f4') {
                            o5++;
                        } else if (line.charAt(n) == '\u00fb') {
                            u5++;
                        } else if (line.charAt(n) >= 'a' && line.charAt(n) <= 'z') {
                            consoantes++;
                        } else if (line.charAt(n) == '<') {
                            if (line.charAt(n + 1) == 'b' && line.charAt(n + 2) == 'r' && line.charAt(n + 3) == '>') {
                                br1++;
                                consoantes--;
                                consoantes--;
                            } else if (line.charAt(n + 1) == 't' && line.charAt(n + 2) == 'a'
                                    && line.charAt(n + 3) == 'b' && line.charAt(n + 4) == 'l'
                                    && line.charAt(n + 5) == 'e' && line.charAt(n + 6) == '>') {
                                table++;
                                consoantes--;
                                a--;
                                consoantes--;
                                consoantes--;
                                e--;
                            }

                        }
                    }
                }
                br.close();
            }

            catch (IOException po) {
                po.printStackTrace();
            }

            System.out.println("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") \u00e1(" + a2
                    + ") \u00e9(" + e2 + ") \u00ed("
                    + i2 + ") \u00f3(" + o2 + ") \u00fa(" + u2 + ") \u00e0(" + a3 + ") \u00e8(" + e3 + ") \u00ec(" + i3
                    + ") \u00f2(" + o3 + ") \u00f9(" + u3
                    + ") \u00e3(" + a4 + ") \u00f5(" + o4 + ") \u00e2(" + a5 + ") \u00ea(" + e5 + ") \u00ee(" + i5
                    + ") \u00f4(" + o5 + ") \u00fb(" + u5
                    + ") consoante(" + consoantes + ") <br>(" + br1 + ") <table>(" + table + ") " + Nome_Site);
                    if (sc.hasNextLine()) {
                        Nome_Site = sc.nextLine();
                    } else {
                        break;
                    }
        
                    if (sc.hasNextLine()) {
                        Site = sc.nextLine();
                    } else {
                        break;
                    }
                     a = 0;
                     e = 0;
                     i = 0;
                     o = 0;
                     u = 0;
                     a2 = 0; // 'á'
                     e2 = 0; // 'é'
                     i2 = 0; // 'í'
                     o2 = 0; // 'ó'
                     u2 = 0; // 'ú'
                     a3 = 0; // 'ã'
                     e3 = 0; // 'è'
                     i3 = 0; // 'ì'
                     o3 = 0; // 'ò'
                     u3 = 0; // 'ù'
                     a4 = 0; // 'â'
                     o4 = 0; // 'ô'
                     a5 = 0; // 'ê'
                     e5 = 0; // 'ê'
                     i5 = 0; // 'î'
                     o5 = 0; // 'ô'
                     u5 = 0; 
                     consoantes = 0;
                     br1 = 0;
                     table = 0;
        }
    }
}
