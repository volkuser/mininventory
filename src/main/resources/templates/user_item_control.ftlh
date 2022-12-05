<#--
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit user #${selectedUser.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="email_id">Email</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="email_id" type="text" name="email"
                           value="${selectedUser.email}"
                           class="form-control ${(emailError??)?string('is-invalid', '')} p-2">
                    <#if emailError??>
                        <div class="invalid-feedback p-2">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Employee</label>
            <label>
                <select name="employee" class="form-control">
                    <#list employees as employee>
                        <#if employee.equals(selectedUser.employee)>
                            <option value="${selectedUser.employee.id}" selected>
                                ${selectedUser.employee.surname}</option>
                        <#else>
                            <option value="${employee.id}">${employee.surname}</option>
                        </#if>
                    </#list>
                </select>
            </label>
        </div>
        <a type="button" class="btn btn-primary" href="/user">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/user/more/${selectedUser.id}/delete">Delete</button>
    </form>
</@c.page>-->
