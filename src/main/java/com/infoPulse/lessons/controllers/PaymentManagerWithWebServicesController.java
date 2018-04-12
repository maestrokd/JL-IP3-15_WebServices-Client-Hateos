package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.AssemblerPaymentDTO;
import com.infoPulse.lessons.model.dto.CustomPageImpl;
import com.infoPulse.lessons.model.dto.PageResource;
import com.infoPulse.lessons.model.dto.PaymentDTO;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Payment;
import com.infoPulse.lessons.model.service.CustomerService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.Charset;
import java.util.List;


@RestController
public class PaymentManagerWithWebServicesController {

//    @Autowired
//    UserServiceImpl userServiceImpl;
//    @Autowired
//    RoleServiceImpl roleServiceImpl;

    // Fields
    private CustomerService customerService;

    @Autowired
    Logger logger;


    // Getters and Setters
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    // Methods
    public static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }


    @RequestMapping(value = "/getpayment", method = RequestMethod.GET)
    public ModelAndView getPayment() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/payments/1";

        HttpHeaders headers = createHeaders("user", "user");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<PaymentDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET
                , requestEntity, PaymentDTO.class);
        PaymentDTO article = responseEntity.getBody();

        System.out.println(article);
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("selectedPayment", article);
        modelAndView.setViewName("/all/payments/paymentinfo");
        return modelAndView;
    }


    @RequestMapping(value = "/getallpayments/{customerId}", method = RequestMethod.GET)
    public ModelAndView getAllPaymentsByCustomer(@PathVariable int customerId) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/paymentsall/" + customerId;

        HttpHeaders headers = createHeaders("user", "user");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<List<PaymentDTO>> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.GET
                , requestEntity
                , new ParameterizedTypeReference<List<PaymentDTO>>() {});
        List<PaymentDTO> article = responseEntity.getBody();

//            , new ParameterizedTypeReference<PageResource>() {});

        System.out.println(article.size());
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paymentList", article);
        modelAndView.setViewName("/all/payments/allpaymentsbycustomer");
        return modelAndView;
    }


    @RequestMapping (value = "/all/payments/add/{phoneNumber}", method = RequestMethod.GET)
    public ModelAndView showCreatePaymentForm(
            @PathVariable("phoneNumber")
                    String phoneNumber
    ) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("phoneNumber", phoneNumber);
        modelAndView.addObject("paymentForm", new Payment());
        modelAndView.setViewName("all/payments/paymentcreateform");
        return modelAndView;
    }


    @RequestMapping(value = "/createpayment", method = RequestMethod.POST)
    public ModelAndView doCreatePayment(
            @ModelAttribute(name = "paymentForm")
                    Payment paymentForm
            , RedirectAttributes redirectAttributes
    ) {
        Customer customer = customerService.findCustomerByPhoneNumber(paymentForm.getCustomer().getPhoneNumber());
        paymentForm.setCustomer(customer);
//        paymentForm.setDateAsString("11-11-1111");

        PaymentDTO paymentDTO = AssemblerPaymentDTO.getInstance().getPaymentDTO(paymentForm);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/paymentcreate";

        HttpHeaders headers = createHeaders("admin", "admin");
        headers.setContentType(MediaType.APPLICATION_JSON);

//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonInString = null;
//        try {
//            jsonInString = objectMapper.writeValueAsString(paymentDTO);
//            System.out.println("JSON" + jsonInString);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<PaymentDTO> requestEntity = new HttpEntity<>(paymentDTO, headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<PaymentDTO> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.POST
                , requestEntity
                , PaymentDTO.class);
        PaymentDTO paymentId = responseEntity.getBody();

        System.out.println(paymentId);
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("message", "Payment " + paymentId.getPaymentId() + " created successfully");
        modelAndView.setViewName("redirect:/all/customers/" + paymentForm.getCustomer().getPhoneNumber());
        return modelAndView;
    }









    @RequestMapping(value = "/getallpaymentj", method = RequestMethod.GET)
    public ModelAndView getAllPaymentJ() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/paymentz";

        HttpHeaders headers = createHeaders("user", "user");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<CustomPageImpl<PaymentDTO>> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.GET
                , requestEntity
//                , new ParameterizedTypeReference<Page<PaymentDTO>>() {}
                , new ParameterizedTypeReference<CustomPageImpl<PaymentDTO>>() {}
                );
        Page<PaymentDTO> articlePage = responseEntity.getBody();

//        for (PaymentDTO paymentDTO : articlePage){
//
//        }

        List<PaymentDTO> articleList = articlePage.getContent();

//        ObjectMapper objectMapper = new ObjectMapper();
//        List<PaymentDTO> articleList = objectMapper.readValue(articlePage.getContent(), List<PaymentDTO>);

        System.out.println(articlePage);
        System.out.println(articleList);
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //Client
    @RequestMapping(value = "/getallpayments", method = RequestMethod.GET)
    public ModelAndView getAllPayment(
//            @PathVariable(name = "size") Integer size,
//            @PathVariable(name = "page") Integer page
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all/payments/allpaymentslist");
        return modelAndView;
    }

    //server:8090 -> client:8080 -> front:8080


    @RequestMapping(value = "/getallpaymentjs", method = RequestMethod.GET)
    public ResponseEntity getAllPaymentJs() {

//    public ResponseEntity<Page<PaymentDTO>> getAllPaymentJs() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/paymentz";

        HttpHeaders headers = createHeaders("user", "user");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<PageResource> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.GET
                , requestEntity
                , new ParameterizedTypeReference<PageResource>() {});
        PageResource articlePage = responseEntity.getBody();

        //ObjectMapper objectMapper = new ObjectMapper();
        //List<PaymentDTO> articleList = objectMapper.readValue(articlePage.getContent(), List<PaymentDTO>);
//
          List<PaymentDTO> articleList = articlePage.getContent();

//        System.out.println(articleList);
//        System.out.println("============================");


        return responseEntity;
    }



}
