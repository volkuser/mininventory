<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit employee #${selectedEmployee.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="surname_id">Surname</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="surname_id" type="text" value="${selectedEmployee.surname}" name="surname"
                           class="form-control ${(surnameError??)?string('is-invalid', '')} p-2">
                    <#if surnameError??>
                        <div class="invalid-feedback p-2">
                            ${surnameError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <a type="button" class="btn btn-primary" href="/employee">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/employee/more/${selectedEmployee.id}/delete">Delete</button>
    </form>
</@c.page>