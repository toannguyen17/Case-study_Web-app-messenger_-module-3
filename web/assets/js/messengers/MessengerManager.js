var MessengerManager = function () {
    this.user_id = null;
    this.group   = null;

    this.el_chat_h_avatar   = $('#chat_h_avatar');
    this.el_chat_h_name     = $('#chat_h_name');

    this.el_messangers      = $('#messangers');
    this.el_btn_send        = $('#btn_send');
    this.el_form_messenger  = $('#form_messenger');
    this.el_input_messenger = $('#input_messenger');

    this.el_form_messenger.on('submit', (e)=>{
        e.preventDefault();
        this.send();
    });

    this.el_btn_send.on('click', ()=>{
        this.send();
    });
};

MessengerManager.prototype.send = function () {
    let value = this.el_input_messenger.val().trim();
    if (this.user_id != null && value.length > 0){
        let message = new Messages.chat(this.user_id, value);
        AppMessenger.send(message);
        this.el_input_messenger.val("");
    }
}

MessengerManager.prototype.onChat = function (data) {
    console.log(data);
    if (data.chats == void 0 && data.messenger == void 0){
        // trò chuyện mới
        this.newContact(data);

    }else if(data.chats != void 0){
        // tải lại cuộc trò chuyện cũ
        this.newContact(data);
        this.loadChats(data.chats);

    }else if(data.messenger != void 0){
        // nhận được tin nhắn
        this.onRealMess(data);
    }
}

MessengerManager.prototype.newContact = function (data) {
    this.el_messangers.children().remove();

    this.user_id = data.user.id;
    this.group   = null;

    this.el_chat_h_avatar.attr('src', data.user.avatar);
    this.el_chat_h_name.text(data.user.last_name + ' ' + data.user.first_name);
}

MessengerManager.prototype.loadChats = function (data) {
    for (let i = data.length - 1; i >= 0; i--){
        let message = data[i];
        this.createContentEl(message);
    }
    this.scrollTop(this.scrollBottonPoint());
}

MessengerManager.prototype.onRealMess = function (data) {
    if (data.add_contact != void 0){
        console.log(data.user)
        AppMessenger.ManagerContact.prepend(data.user);
    }

    if (data.messenger.user_id == AppMessenger.me_id || data.messenger.user_id == this.user_id){
        this.createContentEl(data.messenger);

        let parent  = this.el_messangers[0].parentElement;
        let content = this.el_messangers[0];

        let allowScrollBotton = content.offsetHeight - parent.offsetHeight - 100;
        let curentScroll      = parent.scrollTop;

        if (allowScrollBotton > 100 && curentScroll >= allowScrollBotton){
            this.scrollTop(this.scrollBottonPoint());
        }
    }else{
        this.pushNotification(data);
    }
}

MessengerManager.prototype.createContentEl = function (message) {
    if (this.group == null || this.group.user_id != message.user_id){
        let isMe = message.user_id == AppMessenger.me_id ? true : false;

        this.group = new MessengerGroup(isMe);

        this.group.user_id = message.user_id;
        this.el_messangers.append(this.group.getElement());
    }
    this.group.append(message);
}

MessengerManager.prototype.pushNotification = function (data) {
    let message = data.messenger.text;
    if (message.length > 40){
        message = message.substring(0, 40) + "...";
    }

    let name = data.user.last_name + ' ' + data.user.first_name;

    ToastPush.push('', name, message);
}


MessengerManager.prototype.scrollTop = function (point) {
    this.el_messangers[0].parentElement.scrollTop = point;
}

MessengerManager.prototype.scrollBottonPoint = function () {
    let parent    = this.el_messangers[0].parentElement;
    let mess = this.el_messangers[0];

    let parentH = parent.offsetHeight;
    let messH   = mess.offsetHeight;

    return messH-parentH;
}

