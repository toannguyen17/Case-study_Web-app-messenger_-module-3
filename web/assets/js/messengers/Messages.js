var Messages = Messages || {};

Messages.logout = {action: "logout"};

Messages.search = function (phone) {
    this.action = "search";
    this.data   = phone;
}

Messages.chat = function (user_id, text = null) {
    this.action  = "chat";
    this.user_id = user_id;
    if (text !== null){
        this.text = text;
    }
}

Messages.loadMessenger = function (user_id, last_id) {
    this.action   = "chat";
    this.user_id = user_id;
    this.last_id = last_id;
}

Messages.loadContact = function (type, data = null) {
    this.action = "contact";
    this.type   = type;
    if (data != null){
        this.data = data;
    }
}


