var Dropdown = function (reference, menu, option = null) {
    this.referenceElement = $(reference)[0];
    this._menu            = $(menu)[0];
    if (option === null){
        option = {placement: "bottom"};
    }
    this._popper = new Popper(this.referenceElement, this._menu, option);
    this.init();
}

Dropdown.prototype.init = function () {
    this.referenceElement.addEventListener('click', function () {
        this.toggle();
    }.bind(this))
}

Dropdown.prototype.toggle = function () {
    let menu = $(this._menu);
    let isVisible = menu.hasClass("d-block");
    console.log(isVisible);
    if (isVisible) {
        $(this._menu).removeClass("d-block");
        $(this._menu).addClass("d-none");
    }else{
        $(this._menu).removeClass("d-none");
        $(this._menu).addClass(" d-block");
    }
}

Dropdown.prototype.update = function() {
    if (this._popper !== null) {
        this._popper.scheduleUpdate()
    }
}
