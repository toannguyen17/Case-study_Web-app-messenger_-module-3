
const ID_MODAL_REGISTER        = 'register_box'
const ID_FORM_REGISTER         = 'form_register'
// const ID_STATUS_PHONE_REGISTER = 'status_phone_register'
// const ID_STATUS_PASS_REGISTER  = 'status_pass_register'

var FormRegister = function () {
    this._form         = $(HASHTAG + ID_FORM_REGISTER);
    //this._status_phone = $(HASHTAG + ID_STATUS_PHONE_LOGIN);
    //this._status_pass  = $(HASHTAG + ID_STATUS_PASS_LOGIN);
    this.init();
}

FormRegister.prototype.init = function () {
    this._form.on('submit', function(e){
        e.preventDefault();

        let form = this._form[0];
        form.classList.add('was-validated');
        if (form.checkValidity() === false) {
            e.stopPropagation();
            ToastPush.push('', 'Đăng ký', 'Các trường đăng ký không hợp lệ.');
        }else{
            this.register();
        }
    }.bind(this));
}

FormRegister.prototype.register = function () {
    let data_form = this._form.serialize();
    let url       = this._form.attr("action");

    $.ajax({
        url:  url,
        type: 'POST',
        data:  data_form,
        success: function(result){
            if (result.status == 1){
                $('.toast').remove();
                ToastPush.push('', 'Đăng nhập', 'Đăng ký thành công.');
                location.reload();
            }else{
                result.errors.forEach(function (element) {
                    ToastPush.push('', 'Đăng nhập', element);
                });
            }
        }
    });
}


