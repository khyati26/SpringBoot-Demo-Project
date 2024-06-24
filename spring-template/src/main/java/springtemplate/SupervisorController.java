package springtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SupervisorController {

    @Autowired
    private ExternalAPI externalAPI;

    @Autowired
    @Qualifier("NotificationValidator")
    private Validator notificationValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(notificationValidator);
    }

    @GetMapping("/supervisors")
    public ResponseEntity<ResponseVo> getSupervisors() {
        List<Manager> managerList = this.externalAPI.getManagersList();
        managerList =
                managerList.stream().filter(manager -> !Utility.isNumeric(manager.getJurisdiction())).collect(Collectors.toList());

        Comparator<Manager> managerComparator = Comparator
                .comparing(Manager::getJurisdiction)
                .thenComparing(Manager::getLastName)
                .thenComparing(Manager::getFirstName);

        managerList = managerList.stream()
                .sorted(managerComparator)
                .collect(Collectors.toList());

        List<String> supervisorList =
                managerList.stream().map(manager -> manager.getJurisdiction() + "-" + manager.getLastName() + "," + manager.getFirstName()).collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseVo(supervisorList));
    }

    @PostMapping("/notifiation")
    public ResponseEntity<ResponseVo> addNotification(@Validated @RequestBody NotificationReq notificationReq,
                                                      BindingResult result) {
        if (result.hasErrors()) {
            ResponseVo responseVo = new ResponseVo();
            responseVo.setErrors(result.getFieldErrors().stream().map(fieldError -> fieldError.getCode()).collect(Collectors.toList()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseVo);
        } else {
            System.out.println(notificationReq);
        }
        return ResponseEntity.ok(new ResponseVo());
    }

}
