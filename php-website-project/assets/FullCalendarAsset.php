<?php

namespace app\assets;

use yii\web\AssetBundle;

class FullCalendarAsset extends AssetBundle
{
    public $basePath = '@webroot';
    public $baseUrl = '@web';

    public $js = [
        'js/fullcalendar/index.global.min.js',
    ];

    public $depends = [
        'yii\web\JqueryAsset',
    ];

}