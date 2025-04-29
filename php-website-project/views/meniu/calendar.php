<?php
/** @var array $days*/

    $this->registerJsFile(Yii::$app->request->baseUrl.'/js/fullcalendar/index.global.js',  ['depends' => [\yii\web\JqueryAsset::class]]);


?>

<div id="calendar"></div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const calendarEl = document.getElementById('calendar');
        const calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',

            eventSources: [
                {
                    events: [

                        <?php foreach($days as $day): ?>
                        {
                            start : '<?= $day->day ?>',
                            end : '<?= $day->day ?>',
                            display : 'background',
                            color : 'blue'
                        },
                        <?php endforeach; ?>
                    ]
                }
            ]
        });
        calendar.render();
    });
</script>
