var ToastPush    = null;
var AppMessenger = null;

window.addEventListener('DOMContentLoaded', (event) => {
    ToastPush    = new Toasts();
    AppMessenger = new App();
    AppMessenger.init();
});
