export function showEvents(events) {
    const section = document.querySelector("#events");
    console.log(section);

    // loop over each of my movies and add a nested element
    for (let i = 0; i < events.length; i++) {
        const event = events[i];

        addEventSection(section, event)
    }
}

export function addEventSection(section, event) {
    const requestVal = event.specialRequests ? event.specialRequests : "n/a";
    section.innerHTML += `<tr>
                            <td>${event.id}</td>
                            <td>${event.date}</td>
                            <td>${event.location}</td>
                            <td>${event.genres}</td>
                            <td>${requestVal}</td>
                            <td>${event.equipment}</td>
                            <td><a href="#" class="edit">Edit</a></td>
                            <td><a href="#" class="delete">Delete</a></td>
                        </tr>`;
}

export function testWorld() {
    console.log("helloWorld")
}