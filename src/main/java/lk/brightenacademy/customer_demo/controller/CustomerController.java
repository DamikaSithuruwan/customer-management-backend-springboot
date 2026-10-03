package lk.brightenacademy.customer_demo.controller;

import lk.brightenacademy.customer_demo.dto.CustomerDataDTO;
import lk.brightenacademy.customer_demo.dto.CustomerDetailDTO;
import lk.brightenacademy.customer_demo.dto.CustomerInsertDTO;
import lk.brightenacademy.customer_demo.dto.CustomerSearchRequest;
import lk.brightenacademy.customer_demo.entity.Customer;
import lk.brightenacademy.customer_demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDataDTO add(@RequestBody CustomerInsertDTO requestData){
        Customer customer = new Customer();
        customer.setName( requestData.getName() );
        customer.setMobile(requestData.getMobile());
        customer.setNic( requestData.getNic() );
        customer.setDob( requestData.getDob() );
        customer.setGender( requestData.getGender() );
        customer.setCity( requestData.getCity() );

        customerRepository.save(customer);

        CustomerDataDTO data = new CustomerDataDTO();
        data.setId(customer.getId());
        data.setMobile(customer.getMobile());
        data.setName(customer.getName());

        return data;
    }

    @PutMapping("/{id}")
    public CustomerDataDTO update(@PathVariable Integer id,@RequestBody CustomerInsertDTO requestData){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
        }
        Customer customer = optionalCustomer.get();

        customer.setName( requestData.getName() );
        customer.setMobile(requestData.getMobile());
        customer.setNic( requestData.getNic() );
        customer.setDob( requestData.getDob() );
        customer.setGender( requestData.getGender() );
        customer.setCity( requestData.getCity() );

        customerRepository.save(customer);

        CustomerDataDTO data = new CustomerDataDTO();
        data.setId(customer.getId());
        data.setMobile(customer.getMobile());
        data.setName(customer.getName());

        return data;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){

        boolean exists = customerRepository.existsById(id);

        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
        }

        customerRepository.deleteById(id);
    }

    @GetMapping
    public Page<CustomerDataDTO> getAll(@PageableDefault(size = 10) Pageable pageable,
                                        @ModelAttribute CustomerSearchRequest searchRequest){

        Specification<Customer> specs = searchRequest.getSpecification();

        Page<Customer> customers = customerRepository.findAll(specs,pageable);
        Page<CustomerDataDTO> dataPage = customers.map(customer -> {
           CustomerDataDTO dataDTO = new CustomerDataDTO();
            dataDTO.setId(customer.getId());
            dataDTO.setMobile(customer.getMobile());
            dataDTO.setName(customer.getName());
            dataDTO.setNic(customer.getNic());
            dataDTO.setCity(customer.getCity());
            return dataDTO;
        });
        return dataPage;
    }

    @GetMapping("/{id}")
    public CustomerDetailDTO getOne(@PathVariable Integer id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
        }
            Customer customer =optionalCustomer.get();

            CustomerDetailDTO data = new CustomerDetailDTO();
            data.setId(customer.getId());
            data.setName(customer.getName());
            data.setMobile(customer.getMobile());
            data.setNic(customer.getNic());
            data.setCity(customer.getCity());
            data.setDob(customer.getDob());
            data.setGender(customer.getGender());
            data.setCreatedAt(customer.getCreatedAt());
            data.setUpdatedAt(customer.getUpdatedAt());

            return data;
    }

}
