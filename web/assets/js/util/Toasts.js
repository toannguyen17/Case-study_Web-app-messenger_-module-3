var Toasts = Toasts || {};
const  TOAST_PUSH_ID = 'toast_push';
Toasts = function () {
    this.toast = $(HASHTAG + TOAST_PUSH_ID);
    this.id = 0;
}
Toasts.prototype.push = function (icon, title, notification) {
    let id_t = ++this.id;
    id_t = 'ts_' + id_t;

    let html = '<div id="' + id_t + '" class="toast" role="alert" aria-live="assertive" aria-atomic="true">\n' +
        '            <div class="toast-header">\n' +
        icon +
        '                <strong class="mr-auto">'+title+'</strong>\n' +
        '                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">\n' +
        '                    <span aria-hidden="true">&times;</span>\n' +
        '                </button>\n' +
        '            </div>\n' +
        '            <div class="toast-body">\n' +
        notification +
        '            </div>\n' +
        '        </div>';

    this.toast.append(html);

    let toast = $(HASHTAG+id_t);
    toast.toast({
        animation: true,
        autohide: true,
        delay: 3000
    }).toast('show');

    toast.on('hidden.bs.toast', function () {
        toast.remove();
    })
}
