
const ID_FORM_LOGIN         = 'form_login'
const ID_STATUS_PHONE_LOGIN = 'status_phone_login'
const ID_STATUS_PASS_LOGIN  = 'status_pass_login'
const HASHTAG               = '#'

var FormLogin = function () {
    this._form         = $(HASHTAG + ID_FORM_LOGIN);
    this._status_phone = $(HASHTAG + ID_STATUS_PHONE_LOGIN);
    this._status_pass  = $(HASHTAG + ID_STATUS_PASS_LOGIN);
    this.init();
}

FormLogin.prototype.init = function () {
    //let self = this;
    this._form.on('submit', function(e){
        e.preventDefault();
        let form = this._form[0];
        form.classList.add('was-validated');
        if (form.checkValidity() === false) {
            e.stopPropagation();
            ToastPush.push('', 'Đăng nhập', 'Tài khoản hoặc mật khẩu không chính xác.');
        }else{
            this.login();
        }
    }.bind(this));
}

FormLogin.prototype.login = function () {
    let data_form = this._form.serialize();
    let url       = this._form.attr("action");

    $.ajax({
        url:  url,
        type: 'POST',
        data:  data_form,
        success: function(result){
            if (result.status == 1){
                $('.toast').remove();
                ToastPush.push('', 'Đăng nhập', 'Đăng nhập thành công.');
                location.reload();
            }else{
                result.errors.forEach(function(element) {
                    ToastPush.push('', 'Đăng nhập', element);
                });
            }
            console.log(result);
        }
    });
}

