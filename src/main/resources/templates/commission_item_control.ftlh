<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit commission #${selectedCommission.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="number_id">Number</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="number_id" type="text" value="${selectedCommission.number}" name="number"
                           class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                    <#if numberError??>
                        <div class="invalid-feedback p-2">
                            ${numberError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="eventDate_id">Event date</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="eventDate_id" type="date" value="${selectedCommission.eventDateAsString}" name="eventDate"
                           class="form-control ${(eventDateError??)?string('is-invalid', '')} p-2">
                    <#if eventDateError??>
                        <div class="invalid-feedback p-2">
                            ${eventDateError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <a type="button" class="btn btn-primary" href="/commission">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/commission/more/${selectedCommission.id}/delete">Delete</button>
    </form>
</@c.page>