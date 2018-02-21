package com.infoPulse.lessons.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoPulse.lessons.model.dto.Assembler;
import com.infoPulse.lessons.model.dto.PaymentDTO;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Payment;
import com.infoPulse.lessons.model.service.CustomerServiceImpl;
import com.infoPulse.lessons.model.service.RoleServiceImpl;
import com.infoPulse.lessons.model.service.UserServiceImpl;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  14.07.2017 for spingSecurityAdv project.
 */

@RestController
public class WebServicesController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    Logger logger;




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
        Customer customer = customerServiceImpl.findCustomerByPhoneNumber(paymentForm.getCustomer().getPhoneNumber());
        paymentForm.setCustomer(customer);
        paymentForm.setDateAsString("11-11-1111");

        PaymentDTO paymentDTO = Assembler.getInstance().getPaymentDTO(paymentForm);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/paymentcreate";

        HttpHeaders headers = createHeaders("admin", "admin");
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(paymentDTO);
            System.out.println("JSON" + jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
        // Обычно используется в сочетании с RestTemplate,
        HttpEntity<PaymentDTO> requestEntity = new HttpEntity<>(paymentDTO, headers);

        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.POST
                , requestEntity
                , Integer.class);
        Integer paymentId = responseEntity.getBody();

        System.out.println(paymentId);
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("message", "Payment " + paymentId + " created successfully");
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
        ResponseEntity<Page<PaymentDTO>> responseEntity = restTemplate.exchange(
                url
                , HttpMethod.GET
                , requestEntity
                , new ParameterizedTypeReference<Page<PaymentDTO>>() {});
        Page<PaymentDTO> articlePage = responseEntity.getBody();

//        for (PaymentDTO paymentDTO : articlePage){
//
//        }

        List<PaymentDTO> articleList = articlePage.getContent();

//        ObjectMapper objectMapper = new ObjectMapper();
//        List<PaymentDTO> articleList = objectMapper.readValue(articlePage.getContent(), List<PaymentDTO>);

        System.out.println(articleList);
        System.out.println("============================");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/getallpayment", method = RequestMethod.GET)
    public ModelAndView getAllPayment() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/all/payments/allpaymentslist");
        return modelAndView;
    }


//    @RequestMapping(value = "/getallpaymentjs", method = RequestMethod.GET)
//    public Page<PaymentDTO> getAllPaymentJs() {
////    public ResponseEntity<Page<PaymentDTO>> getAllPaymentJs() {
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8090/api/paymentz";
//
//        HttpHeaders headers = createHeaders("user", "user");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Представляет объект запроса HTTP или ответа, состоящий из заголовков и тела.
//        // Обычно используется в сочетании с RestTemplate,
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//
//        // ResponseEntity - Расширение HttpEntity, которое добавляет код состояния HttpStatus.
//        ResponseEntity<Page<PaymentDTO>> responseEntity = restTemplate.exchange(
//                url
//                , HttpMethod.GET
//                , requestEntity
//                , new ParameterizedTypeReference<Page<PaymentDTO>>() {});
//        Page<PaymentDTO> articlePage = responseEntity.getBody();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        List<PaymentDTO> articleList = articlePage.getContent();
////        List<PaymentDTO> articleList = objectMapper.readValue(articlePage.getContent(), List<PaymentDTO>);
//
//        System.out.println(articleList);
//        System.out.println("============================");
//
//
//        return articlePage;
//    }



}
