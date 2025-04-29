<?php

namespace app\controllers;

use app\models\Identifier;
use app\models\IdentifierSearch;
use app\models\ProductType;
use yii\helpers\ArrayHelper;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * IdentifierController implements the CRUD actions for Identifier model.
 */
class IdentifierController extends Controller
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
     * Lists all Identifier models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new IdentifierSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Identifier model.
     * @param int $id_identifier Id Identifier
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id_identifier)
    {
        return $this->render('view', [
            'model' => $this->findModel($id_identifier),
        ]);
    }

    /**
     * Creates a new Identifier model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new Identifier();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id_identifier' => $model->id_identifier]);
            }
        } else {
            $model->loadDefaultValues();
        }

        $identifiers = ProductType::find()->select(['id_product_type', 'type'])->asArray()->all();
        $identifierItems = ArrayHelper::map($identifiers, 'id_product_type', 'type');

        return $this->render('create', [
            'model' => $model,
            'identifierItems' => $identifierItems,
        ]);
    }

    /**
     * Updates an existing Identifier model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_identifier Id Identifier
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id_identifier)
    {
        $model = $this->findModel($id_identifier);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id_identifier' => $model->id_identifier]);
        }

        $identifiers = ProductType::find()->select(['id_product_type', 'type'])->asArray()->all();
        $identifierItems = ArrayHelper::map($identifiers, 'id_product_type', 'type');

        return $this->render('update', [
            'model' => $model,
            'identifierItems' => $identifierItems,
        ]);
    }

    /**
     * Deletes an existing Identifier model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_identifier Id Identifier
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id_identifier)
    {
        $this->findModel($id_identifier)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Identifier model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_identifier Id Identifier
     * @return Identifier the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_identifier)
    {
        if (($model = Identifier::findOne(['id_identifier' => $id_identifier])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
