<template>

    <div id="nav">

        <div class="row">

            <div class="col-md-4">

                <router-link to="/practices"><input type="button" value="Practices" class="btn btn-primary"/></router-link>

            </div>

            <div class="col-md-4">

                <router-link to="/patientregistrations">

                    <input v-if="patientid" type="button" value="Patient personal cabinet" class="btn btn-info"/>

                    <input v-else type="button" value="Register new patient" class="btn btn-info"/>

                </router-link>

            </div>

            <div class="col-md-4">
                <a href="https://www.usamco.com/pdf/glossary.pdf" target="_blank">
                    <input type="button" value="Glossary" class="btn btn-info">
                </a>

            </div>

        </div>

    </div>

</template>


<script>
    export default {
        name: 'Home',
        data() {
            return {
                patientid: null,
            }
        },
        created: function () {
            if (localStorage.patientid) {
                this.patientid = localStorage.patientid;
                console.log("Patient id found = " + this.patientid)
            }
        },
    }
</script>

<style>
    h3 {
        margin-bottom: 5%;
    }
</style>