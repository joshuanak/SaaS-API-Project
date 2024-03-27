package edu.greenriver.sdev.saasproject;

import edu.greenriver.sdev.saasproject.db.EventRequestRepository;
import edu.greenriver.sdev.saasproject.db.SoundCloudMixRepository;
import edu.greenriver.sdev.saasproject.domain.EventRequest;
import edu.greenriver.sdev.saasproject.domain.SoundCloudMix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SaasProjectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SaasProjectApplication.class, args);
        EventRequestRepository dbRepo1 = context.getBean(EventRequestRepository.class);
        SoundCloudMixRepository dbRepo2 = context.getBean(SoundCloudMixRepository.class);


        EventRequest event1 = new EventRequest(0, "2024-02-18",
                "Mountaineering Club", "EDM", "b2b with Siid", true);
        EventRequest event2 = new EventRequest(0, "2024-02-16",
                "Vue Lounge", "House", "b2b with Smytti", true);
        EventRequest event3 = new EventRequest(0, "2024-03-9",
                "Cafe Racer", "Bass House", "solo set", true);

        SoundCloudMix mix1 = new SoundCloudMix(0, "https://soundcloud.com/itsnakatani/no-thoughts-head-empty-just-bass-" +
                "house-2?si=243ecb9381fb43e39729c7eb1269cefe&utm_source=clipboard&utm_medium=text&utm_campaign=social_" +
                "sharing", false, "No Thoughts Head Empty Just Bass House 1", "House");
        SoundCloudMix mix2 = new SoundCloudMix(0, "https://soundcloud.com/itsnakatani/reytani-milo-house-presents-" +
                "project-nocturnal-dubstep-trap-house-variety-mix?si=1f9a233abcf542f4b75c97f91cf3ddbc&utm_source=" +
                "clipboard&utm_medium=text&utm_campaign=social_sharing", true, "Dubstep, House",
                "REYTANI - Milo House Presents: Project Nocturnal");

        dbRepo1.save(event1);
        dbRepo1.save(event2);
        dbRepo1.save(event3);

        dbRepo2.save(mix1);
        dbRepo2.save(mix2);
    }
}




