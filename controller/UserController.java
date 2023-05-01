package am.itspace.taskmanagement.controller;

import am.itspace.taskmanagement.entity.User;
import am.itspace.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String users(ModelMap modelMap) {
        List<User> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String userHome() {
        return "user";
    }

    @GetMapping("/users/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("userImage") MultipartFile file,
                          ModelMap modelMap) throws IOException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessageEmail", "Email has already in use");
            return "addUser";
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            if(file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
                return "addUser";
            }

        }
        userService.saveUser(user, file);

        return "redirect:/users";
    }

    @GetMapping(value = "/users/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}

//        HttpServletRequest req

//        @RequestParam(name = "name") String name,
//        @RequestParam(name = "surname") String surname,
//        @RequestParam(name = "email") String email,
//        @RequestParam(name = "role") Role role) {
//       User user = User.builder()
//               .name(name)
//               .surname(surname)
//               .email(email)
//               .role(role)
//               .build();


