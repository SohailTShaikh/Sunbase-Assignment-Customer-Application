package com.SunbaseAssignment.CRUDAppForCustomer.Controllers;




import com.SunbaseAssignment.CRUDAppForCustomer.DTO.CustomerDTO;
import com.SunbaseAssignment.CRUDAppForCustomer.Entities.Customer;
import com.SunbaseAssignment.CRUDAppForCustomer.Repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return
                "redirect:/login?logout";
    }

    @GetMapping("/customersList")
    public String showCustomersList(Model model){
        List<Customer> customers=customerRepository.findAll();
        model.addAttribute("customers",customers);
        return "customers/Index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        CustomerDTO customerDTO=new CustomerDTO();
        model.addAttribute("customerDTO",customerDTO);
        return "customers/CreateCustomer";
    }
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute CustomerDTO customerDTO, BindingResult result){

        Customer customer=new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setStreetName(customerDTO.getStreetName());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customerRepository.save(customer);
             return "redirect:/customersList";
    }

    @GetMapping ("/edit")
    public String showEditPage(Model model, @RequestParam("/customerId") int customerId){
        Customer customer=customerRepository.findById(customerId).get();
        model.addAttribute("customer",customer);

        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setStreetName(customer.getStreetName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCity(customer.getCity());
        customerDTO.setState(customer.getState());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        model.addAttribute("customerDTO",customerDTO);

        return "customers/EditCustomer";
    }

    @PostMapping("/edit")
    public String updateCustomer(
        Model model,@RequestParam("/customerId") int customerId,@Valid @ModelAttribute CustomerDTO customerDTO,BindingResult result){
        Customer customer=customerRepository.findById(customerId).get();
        model.addAttribute("customer",customer);

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setStreetName(customerDTO.getStreetName());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customerRepository.save(customer);

        return "redirect:/customersList";

    }

    @GetMapping ("/delete")
    public String deleteProduct
        (@RequestParam Integer id){
        Customer customer=customerRepository.findById(id).get();
          customerRepository.delete(customer);
        return "redirect:/customersList";
    }
}
