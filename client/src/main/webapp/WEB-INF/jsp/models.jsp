<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal" tabindex="-1" role="dialog" id="deleteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <p id="deleteModalMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-fill btn-danger" onclick="deletePersonData()">Delete</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" tabindex="-1" role="dialog" id="errorModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p id="errorModalMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-fill btn-danger">Close</button>
            </div>
        </div>
    </div>
</div>