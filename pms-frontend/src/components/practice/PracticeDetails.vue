<template>
    <div id="practicedetails">

        <PracticeAttributes v-if="practice" :practice="practice"/>


        <div class="row">

            <div class="col-md-4">

                <router-link :to="{ name: 'providers', params: {practiceid: practiceid} }">
                    <input type="button" value="View providers" class="btn btn-primary"/>
                </router-link>

            </div>

            <div class="col-md-4">

                <router-link to="/practices">
                    <input type="button" value="Back to Practices" class="btn btn-info"/>
                </router-link>

            </div>

            <div class="col-md-4">
                <BackToHome/>
            </div>

        </div>

    </div>


</template>

<script>
    import BackToHome from "../BackToHome";
    import PracticeAttributes from "./PracticeAttributes";
    import {ServicesFactory} from "../../services/ServicesFactory";

    const pmsService = ServicesFactory.get("pms");

    export default {
        name: 'PracticeDetails',
        components: {BackToHome, PracticeAttributes},
        props: ['practiceid'],
        data() {
            return {
                practice: null,
            };
        },
        created: function () {
            this.fetch();
        },
        methods: {
            async fetch() {
                pmsService.getPractice(this.practiceid).then(res => {
                    this.practice = res.data;
                });
            }
        }
    }
</script>

