

// Search contact
var SearchContactElement = function (data) {
    this.id = data.id;
    this.el = document.createElement('div');
    this.el.setAttribute('class', 'contact c_pointer d-flex align-items-center pd-6-15');
    this.el.innerHTML = '<img class="rounded-circle" alt="" src="' + data.avatar + '" height="40" width="40">\n' +
        '<div class="contact_sub text-truncate">\n' +
        '<span>' + data.last_name + ' ' + data.first_name + '</span>\n' +
        '</div>';

    this.el.addEventListener('click', () =>{
        let message_uid = AppMessenger.managerMess.user_id;
        if (this.id != message_uid){
            let message = new Messages.chat(this.id);
            AppMessenger.send(message);
        }
    });
}

SearchContactElement.prototype.getElement = function () {
    return this.el;
}

// Group messenger
var MessengerGroup = function (me= false) {
    this.user_id = null;

    let addClass = me ? ' me' : '';

    this.el = document.createElement('div');
    this.el.setAttribute("class", "group_mes d-flex"+ addClass);
    this.el.innerHTML = '<div class="d-flex align-self-end">\n' +
                '<img class="avatar rounded-circle" alt="" src="user.jpg" height="30" width="30">\n' +
            '</div>\n' +
            '<div class="d-flex m-group_content w-100 flex-column">\n' +
            '</div>\n';
    this.el_group_content = $(this.el.getElementsByClassName('m-group_content')[0]);
    this.el_avatar        = this.el.getElementsByClassName('avatar')[0];

    this.el.G_mess = this;
};

MessengerGroup.prototype.createContent = function (message) {
    if (message.avatar != void 0){
        this.el_avatar.setAttribute("src", message.avatar);
    }

    let content = new MessengerContent(message);
    return content.getElement();
}

MessengerGroup.prototype.prepend = function (message) {
    this.el_group_content.prepend(this.createContent(message));
}

MessengerGroup.prototype.append = function (message) {
    this.el_group_content.append(this.createContent(message));
}

MessengerGroup.prototype.getElement = function () {
    return this.el;
}



// Content messenger
var MessengerContent = function (messenger) {
    this.id   = messenger.id;
    this.time = messenger.time;
    this.text = messenger.text;
    this.user_id = messenger.user_id;
    this.el = document.createElement('div');
    this.el.setAttribute("class", "d-flex m_content w-100");

    this.el.innerHTML = '<div class="m-content-c">\n' +
                '<div class="m-content-text">\n' +
        this.text + '\n' +
                '</div>\n' +
            '</div>\n' +
            '<div class="d-flex align-items-center m-content-o min40"></div>\n';
    this.el.Content_mess = this;
};

MessengerContent.prototype.getElement = function () {
    return this.el;
}

// ItemContact
var ContactItem = function (contact) {
    console.log(contact, this)
    this.user_id = contact.id;
    this.el = document.createElement('div');
    this.el.setAttribute("class", "contact d-flex align-items-center pd-6-10 c_pointer");

    let name = contact.last_name + ' ' + contact.first_name;

    this.el.innerHTML = '<div>\n' +
        '<img class="rounded-circle" alt="" src="' + contact.avatar +'" height="50" width="50">\n' +
        '</div>\n' +
        '<div class="contact_sub">\n' +
        '<div class="text-truncate contact_name">\n' +
        '<span>' + name + '</span>\n' +
        '</div>\n' +
        '<div class="d-flex align-items-center justify-content-left contact_sub_text">\n' +
        '<span class="text-truncate">\n' +

        '</span>\n' +
        '<div class="ml-1 mr-1"></div>\n' +
        '<span class="contact_sub_time"></span>\n' +
        '</div>\n' +
        '</div>';

    this.el.addEventListener('click', () =>{
        let message_uid = AppMessenger.managerMess.user_id;
        if (this.user_id != message_uid){
            let message = new Messages.chat(this.user_id);
            AppMessenger.send(message);
        }
    });
}

ContactItem.prototype.getElement = function () {
    return this.el;
}