// function for events id
const randomId = function (length = 6) {
  return Math.random()
    .toString(36)
    .substring(2, length + 2);
};

// initialize your calendar, once the page's DOM is ready

$("#calendar").evoCalendar({
  todayHighlight: true,
  sidebarDisplayDefault: false,
  eventDisplayDefault: true,
  firstDayOfWeek: 0,
});
//events
var events = [
  {
    id: "09nk7Ts",
    name: "ADDL Elon Musk",
    date: "February/15/2020",
    type: "event",
  },
];

// addEvent
const addEvent = (name, date) => {
  events.push({ id: randomId, name: name, date: date, type: "event" });
  $("#calendar").evoCalendar("addCalendarEvent", {
    id: randomId,
    name: name,
    date: date,
    type: "event",
  });
};

addEvent("visit raouf", "May/05/2024");
addEvent("visit Madjid", "May/06/2024");
addEvent("visit Madjid", "May/06/2024");
