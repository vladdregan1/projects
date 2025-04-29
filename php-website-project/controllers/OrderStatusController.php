<?php

namespace app\controllers;

use app\models\OrderStatus;
use app\models\OrderStatusSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * OrderStatusController implements the CRUD actions for OrderStatus model.
 */
class OrderStatusController extends Controller
{
    /**
     * @inheritDoc
     */
    public function behaviors()
    {
        return array_merge(
            parent::behaviors(),
            [
                'verbs' => [
                    'class' => VerbFilter::className(),
                    'actions' => [
                        'delete' => ['POST'],
                    ],
                ],
            ]
        );
    }

    /**
     * Lists all OrderStatus models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new OrderStatusSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single OrderStatus model.
     * @param int $id_status Id Status
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id_status)
    {
        return $this->render('view', [
            'model' => $this->findModel($id_status),
        ]);
    }

    /**
     * Creates a new OrderStatus model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new OrderStatus();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id_status' => $model->id_status]);
            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);
    }

    /**
     * Updates an existing OrderStatus model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_status Id Status
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id_status)
    {
        $model = $this->findModel($id_status);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id_status' => $model->id_status]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
    }

    /**
     * Deletes an existing OrderStatus model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_status Id Status
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id_status)
    {
        $this->findModel($id_status)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the OrderStatus model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_status Id Status
     * @return OrderStatus the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_status)
    {
        if (($model = OrderStatus::findOne(['id_status' => $id_status])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
