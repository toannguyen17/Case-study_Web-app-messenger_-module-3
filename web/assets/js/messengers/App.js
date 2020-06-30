var App = App || function(){};

const CLASS_SEARCH_CONTACT = '.search_contact';
const CLASS_LIST_CONTACT   = '.list_contact';
const CLASS_LIST_SEARCH    = '.list_search';

App.prototype.init = function(){
    this.connectServer();
    this.regEvent();

    this.managerMess = new MessengerManager();

    // el
    this.el_list_contact = $('.list_contact');
    this.el_list_search  = $('.list_search');
}

App.prototype.regEvent = function(){
    new Dropdown('#btn_setting_more','#setting_more', {placement: "bottom-start"});

    // on search
    this.regEventSearch();

    // click logout
    $("#logout").on('click', function () {
        this.logout();
    }.bind(this));

    $("#btn_changer_avatar").on('click', function () {
        this.changerAvatar();
    }.bind(this));
}

App.prototype.regEventSearch = function(){
    $(CLASS_SEARCH_CONTACT).on('input', function () {
        let value = this.value;
        let check = Helpers.validPhone(value);
        if (check){
            let message = new Messages.search(value);
            AppMessenger.send(message);
        }else{
            AppMessenger.el_list_search.children().remove();
            AppMessenger.el_list_search.addClass("d-none");
            AppMessenger.el_list_contact.removeClass("d-none");
        }
    });
}

App.prototype.connectServer = function(){
    if (this.connection == void 0)
        this.connection = new Connection();

    this.connection.obj = this;
    this.connection.connect();
}

App.prototype.onmessage = function(message){
    message = this.connection._decodeMessage(message);
    console.log(message)
    let action = message.action;
    switch (action) {
        case "me_id":
            AppMessenger.me_id = message.me;
            break;

        case "chat":
            AppMessenger.managerMess.onChat(message);
            break;

        case "search":
            if (message.data != void 0 && message.data.length > 0){
                this.el_list_search.removeClass("d-none");
                this.el_list_contact.addClass("d-none");
                message.data.forEach(function (data) {
                    let el = new SearchContactElement(data);
                    this.el_list_search.prepend(el.getElement());
                }.bind(this));
            }
            break;

        case "logout":
            location.reload();
            break;
    }
}

App.prototype.send = function(object){
    this.connection.socket.send(JSON.stringify(object));
}

App.prototype.logout = function(){
    this.send(Messages.logout);
}

App.prototype.changerAvatar = function () {
    if (this.inputAvatar == void 0){
        this.inputAvatar = document.createElement('input');
        this.inputAvatar.setAttribute('type', 'file');
        this.inputAvatar.setAttribute('accept', 'image/png, image/jpeg');
        this.inputAvatar.addEventListener('change', ()=>{
            Helpers.uploadAvatar(this.inputAvatar);
        });
    }
    this.inputAvatar.click();
}
