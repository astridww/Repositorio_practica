package IntegracionBackFront.backfront.Config.Cloudinary;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private String cloudName;
    private String apiKey;
    private String apiSecret;

    @Bean //Sirve para que este metodo pueda ser inyectado sobre otras clases que utilizaremos
    public Cloudinary cloudinary(){

        //Cargando todas las variables del archivo .env
        Dotenv dotenv = Dotenv.load();

        //Crear un Map para almacenar la configuración
        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"));
        config.put("api_key", dotenv.get("CLOUDINARY_API_KEY"));
        config.put("api_secret", dotenv.get("CLOUDINARY_API_SECRET"));

        //Retornar una nueva instancia de cloudinary con la configuración cargada
        return new Cloudinary(config);
    }
}
