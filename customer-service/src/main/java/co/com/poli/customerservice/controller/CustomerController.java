package co.com.poli.customerservice.controller;

import co.com.poli.customerservice.helpers.ErrorMessage;
import co.com.poli.customerservice.helpers.Response;
import co.com.poli.customerservice.helpers.ResponseBuild;
import co.com.poli.customerservice.persistence.entity.Customer;
import co.com.poli.customerservice.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }
        customerService.save(customer);
        return builder.success(customer);
    }
    @DeleteMapping("/{numberID}")
    public Response delete(@PathVariable("numberID") String numberID){
        Customer customer = (Customer) findByNumberID(numberID).getData();
        if(customer==null){
            return builder.failed("Not found");
        }
        return builder.success(customer);
    }

    @GetMapping
    public Response findAll(){
        return builder.success(customerService.findAll());
    }

    @GetMapping("/numberID/{numberID}")
    public Response findByNumberID(@PathVariable("numberID") String numberID){
        return builder.success(customerService.findByNumberID(numberID));
    }

    @GetMapping("/{id}")
    public Response findByID(@PathVariable("id") Long id){
        return builder.success(customerService.findById(id));
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
