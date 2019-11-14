package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.converter.UserConverter;
import py.com.econtreras.ecommerceadmin.entity.ConfirmationToken;
import py.com.econtreras.ecommerceadmin.entity.Person;
import py.com.econtreras.ecommerceadmin.entity.User;
import py.com.econtreras.ecommerceadmin.repository.ConfirmationTokenRepository;
import py.com.econtreras.ecommerceadmin.repository.UserRepository;
import py.com.econtreras.ecommerceadmin.service.impl.EmailSenderService;

@Controller
public class UserAccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, UserBean user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, UserBean user)
    {

        User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmail());
        User existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if(existingUser != null || existingUserByUsername != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");

        }
        else
        {
            Person person = new Person();
            person.setName(user.getName());
            person.setLastname(user.getLastName());
            py.com.econtreras.ecommerceadmin.entity.User entity = userConverter.build(user);
            entity.setPerson(person);
            userRepository.save(entity);

            ConfirmationToken confirmationToken = new ConfirmationToken(entity);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIdIgnoreCase(token.getUser().getPerson().getEmail());
            //user.setActive(1);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}