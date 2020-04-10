<template>

    <div id="practice-departments">

        <div class="row">

            <div class="col-md-7">

                <button class="btn btn-light"
                        type="button"
                        data-toggle="collapse"
                        data-target="#departments-list"
                        aria-expanded="false"
                        aria-controls="departments-list">
                    Show <b><u>{{practice.name}}</u></b> departments
                </button>

            </div>

        </div>

        <div class="collapse" id="departments-list">
            Practice {{practice.name}} departments
        </div>

    </div>
    
</template>

<script>
    export default {
        name: "Departments",
        props: {
            practice: Object
        }
    }
</script>
