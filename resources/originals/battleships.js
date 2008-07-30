
function init() {
  Battleships.initPerson(setPerson);
  var rows = new Array(7);
  var cols = new Array(8);
  dwr.util.addRows("map", rows, cols, { cellCreator:customCellCreator });
}

function customCellCreator(options) {
  var td = document.createElement("td");
  td.id = "r" + options.rowNum + "c" + options.cellNum;
  var position = { row:options.rowNum, col:options.cellNum };
  td.onclick = function() { Battleships.shoot(position) };
  return td;
}

var oldPosId = null;
function setPerson(person) {
  dwr.util.setValues(person);
  var id = "r" + person.position.row + "c" + person.position.col;
  dwr.util.byId(id).className = "home";
  if (oldPosId) {
    dwr.util.byId(oldPosId).className = "";
  }
  oldPosId = id;
}

function sendMessage() {
  Battleships.addMessage(dwr.util.getValue("text"));
  dwr.util.setValue("text", "");
}

function serverUpdate(messages, players) {
  dwr.util.removeAllOptions("chatlog");
  dwr.util.addOptions("chatlog", messages, formatChatLine, { escapeHtml:false });
  dwr.util.removeAllRows("scores");
  dwr.util.addRows("scores", players, [formatNameForScoresheet,formatScoreForScoresheet],
    { escapeHtml:false });
}

function formatChatLine(message) {
  return "<span class='chatname'>" + dwr.util.escapeHtml(message.author.name) + ": </span>" +
         "<span class='chattext' style='color:" + message.author.color + ";'>" +
         dwr.util.escapeHtml(message.text) + "</span>";
}

function formatNameForScoresheet(person) {
  return "<span style='color:" + person.color + ";'>" + dwr.util.escapeHtml(person.name) + "</span>&nbsp;";
}

function formatScoreForScoresheet(person) {
  return "<span style='color:" + person.color + ";'>" + person.score + "</span>";
}

function setName() {
  Battleships.setName(dwr.util.getValue("name"));
}

function move() {
  var position = {
    row:dwr.util.getValue("position.row"),
    col:dwr.util.getValue("position.col")
  };
  Battleships.move(position, setPerson);
}

