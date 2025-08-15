package IntegracionBackFront.backfront.Controller.Products.Categories;

import IntegracionBackFront.backfront.Models.DTO.Categories.CategoryDTO;
import IntegracionBackFront.backfront.Services.Categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Apicategory")
public class CategoriesController {

    //Inyectar la clase Service
    @Autowired
    private CategoryService service;

    @GetMapping("/getDataCategories")
    private ResponseEntity<Page<CategoryDTO>> getData(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size){
        //parte 1. Se evalúa cuantos registros desea por página el usuario
        //teniendo como máximo 50 registros por página
        if (size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño de la página debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }

        //parte 2. Invocando el metodo getAllCategories contenido en el service y guardamos los datos
        //en el objeto category
        //Si no hay datos category = null de lo contrario no será nulo
        Page<CategoryDTO> category = service.getAllCategories(page, size);
        if (category == null){
            ResponseEntity.badRequest().body(Map.of(
               "status", "No hay categorias registradas"
            ));
        }
        return ResponseEntity.ok(category);
    }
}
