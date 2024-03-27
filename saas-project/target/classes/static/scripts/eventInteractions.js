import { showEvents } from './eventsData.js';

// wait until the page loads
window.onload = async function() {
    // http://localhost:3030
    const uri = `${window.location.origin}/api/v1/events/all`;
    const config = {
        method: 'get'
    }

    const response = await fetch(uri, config);
    const data = await response.json();
    showEvents(data);

    // select the form button and handle form submission
    const button = document.querySelector("button");
    button.onclick = addEvent;

    // select all edit links
    const editLinks = document.querySelectorAll('.edit');
    for (const link of editLinks) {
        link.onclick = editEvent;
    }

    const deleteLinks = document.querySelectorAll('.delete');
    for (const link of deleteLinks) {
        link.onclick = deleteEvent;
    }


}

async function editEvent(evt) {

    // look for the ID we are editing
    const editLink = evt.target;
    const row = editLink.parentElement.parentElement;
    const cells = row.children;

    const id  = cells[0].innerHTML;
    console.log(`Editing id ${id}`);

    const date = cells[1].innerHTML;
    const location = cells[2].innerHTML;
    const genres = cells[3].innerHTML;
    const requests = cells[4].innerHTML;
    const equipment = cells[5].innerHTML;
    console.log(`Editing location ${location}`);

    function equipmentStatus(val) {
        if (val === "true") {
            cells[5].innerHTML = `<input type="checkbox" id="equipment" checked>`
        } else {
            cells[5].innerHTML = `<input type="checkbox" id="equipment">`
        }
    }

    // replace the text with an input element
    cells[1].innerHTML = `<input type="text" id="updateDate" value="${date}">`;
    cells[2].innerHTML = `<input type="text" id="updateLocation" value="${location}">`;
    cells[3].innerHTML = `<input type="text" id="updateGenres" value="${genres}">`;
    cells[4].innerHTML = `<input type="text" id="updateRequests" value="${requests}">`;
    // helper method to set checkbox to checked if the equipment string value is true
    equipmentStatus(equipment);
    cells[6].innerHTML = `<a href="#" class="save">Save</a>`;

    const updateEvents = document.querySelectorAll('.save');
    for (const link of updateEvents) {
        link.onclick = updateEvent;
    }

    async function updateEvent(evt) {
        console.log("event save button pressed");

        const editLink = evt.target;
        const row = editLink.parentElement.parentElement;
        const cells = row.children;
        const id  = cells[0].innerHTML;

        const updateDate = document.querySelector('#updateDate').value;
        const updateLocation = document.querySelector('#updateLocation').value;
        const updateGenres = document.querySelector('#updateGenres').value;
        const updateRequests = document.querySelector('#updateRequests').value;
        const updateEquipment = cells[5].innerHTML;

        cells[1].innerHTML = updateDate;
        cells[2].innerHTML = updateLocation;
        cells[3].innerHTML = updateGenres;
        cells[4].innerHTML = updateRequests;
        cells[5].innerHTML === "true" ?
            cells[5].innerHTML = "true" :
            cells[5].innerHTML = "false";
        cells[6].innerHTML = `<a href="#" class="edit">Edit</a>`;

        const newEvent = {
            date: `${updateDate}`,
            location: `${updateLocation}`,
            genres: `${updateGenres}`,
            specialRequests: `${updateRequests}`,
            equipment: cells[5].innerHTML === "true"
        }

        const uri = `${window.location.origin}/api/v1/events/${id};`
        const config = {
            method: "put",
            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(newEvent)
        }

        await fetch(uri, config);
    }
}

async function saveEvent(evt) {
    console.log(evt.target);
}

async function deleteEvent(evt) {
    console.log(evt.target);
    const deleteLink = evt.target;
    const row = deleteLink.parentElement.parentElement;
    const cells = row.children;
    //
    const id  = cells[0].innerHTML;

    // send the fetch request
    const uri = `${window.location.origin}/api/v1/events/${id};`
    const config = {
        method: "delete"
    }
    await fetch(uri, config);

    // how to remove the row?
    row.remove();
}

async function addEvent() {
    event.preventDefault();

    // console.log("button clicked");

    const newEvent = {
        date: document.querySelector("#date").value,
        location: document.querySelector("#location").value,
        genres: document.querySelector("#genres").value,
        specialRequests: document.querySelector("#requests").value,
        equipment: document.querySelector("#equipment").checked
    }

    console.log('special requests: ' + newEvent.specialRequests);

    const uri = `${window.location.origin}/api/v1/events/`;
    const config = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(newEvent)
    }

    const response = await fetch(uri, config);
    const events = await response.json();
    console.log(response);
    console.log(events);

    const section = document.querySelector("#events");
    addEventSection(section, events);
}

function addEventSection(section, event) {

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