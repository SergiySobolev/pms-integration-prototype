<template>
    <div class="container border border-primary">
        <h2>Appointment types</h2>

        <div class="card">

            <h5 class="card-header">Office visit</h5>

            <div class="card-body">
                <h5 class="card-title"> {{practiceid}} {{providerid}} {{departmentid}}</h5>
                <p class="card-text">Some Info</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>

        </div>

    </div>
    
</template>

<script>
    export default {
        name: 'ProviderAppointmentTypes',
        props : {
            practiceid: String,
            providerid: String,
            departmentid: String
        },
        data() {
            return {
                appointmentstypeinfo: null,
            };
        },
    }
</script>
