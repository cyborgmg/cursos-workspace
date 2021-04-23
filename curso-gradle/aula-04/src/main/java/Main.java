import org.apache.commons.codec.digest.DigestUtils;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class Main {

    public static void main(String[] args) {

        if(args.length == 0){
            throw new IllegalArgumentException("Por favor informar texto para geracao de hash");
        }
        System.out.println(md5Hex(args[0]));

    }

}
